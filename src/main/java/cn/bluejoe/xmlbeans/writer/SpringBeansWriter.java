package cn.bluejoe.xmlbeans.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.bluejoe.xmlbeans.SpringBeans;
import cn.bluejoe.xmlbeans.formater.ValueFormaterFactory;
import cn.bluejoe.xmlbeans.node.BeansNode;
import cn.bluejoe.xmlbeans.node.value.EntityNode;
import cn.bluejoe.xmlbeans.node.value.ValueNodeDelegate;
import cn.bluejoe.xmlbeans.writer.strategy.BeanPropertySelectionStrategy;
import cn.bluejoe.xmlbeans.writer.strategy.DumpAnnotatedProperties;
import cn.bluejoe.xmlbeans.writer.strategy.NoSuitableBeanWritterFormatException;

/**
 * @author bluejoe2008@gmail.com
 */
public class SpringBeansWriter
{
	BeanPropertySelectionStrategy _beanPropertySelectionStrategy;

	/**
	 * 值格式化工厂
	 */
	ValueFormaterFactory _valueFormaterFactory;

	public SpringBeansWriter()
	{
		_beanPropertySelectionStrategy = new DumpAnnotatedProperties();
	}

	public SpringBeansWriter(BeanPropertySelectionStrategy beanPropertySelectionStrategy)
	{
		_beanPropertySelectionStrategy = beanPropertySelectionStrategy;
	}

	public void setValueFormaterFactory(ValueFormaterFactory valueFormaterFactory)
	{
		_valueFormaterFactory = valueFormaterFactory;
	}

	private void write(BeansNode beansNode, List<FilteredOutput> outputs) throws Exception
	{
		List<ValueNodeDelegate> beanNodes = beansNode.getBeanNodes();

		for (FilteredOutput output : outputs)
		{
			List<ValueNodeDelegate> matches = new ArrayList<ValueNodeDelegate>();

			for (ValueNodeDelegate node : beanNodes)
			{
				if (output.getFilter().matches(((EntityNode<?>) node.getDelegatedValueNode())))
				{
					matches.add(node);
				}
			}

			BeansNode filteredBeansNode = new BeansNode(matches);
			write(filteredBeansNode, output.getOutputStream());

			beanNodes.removeAll(matches);
		}

		if (!beanNodes.isEmpty())
		{
			throw new BeanNodesNotWritten(beanNodes);
		}
	}

	private void write(BeansNode beansNode, OutputStream outputStream) throws Exception
	{
		Document document = DocumentHelper.createDocument();
		Element beansElement = document.addElement("SpringBeans");

		beansNode.writeTo(beansElement);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		format.setIndent("\t");
		format.setOmitEncoding(false);
		format.setSuppressDeclaration(false);
		XMLWriter writer = new XMLWriter(outputStream, format);
		writer.write(beansElement.element("beans"));
		writer.flush();
		outputStream.close();
		writer.close();
		outputStream = null;
		writer = null;
	}

	public void write(SpringBeans beans, File xmlFile) throws Exception
	{
		BeansNode beansNode = writeBeans(beans);
		if (!xmlFile.exists())
			xmlFile.createNewFile();

		write(beansNode, new FileOutputStream(xmlFile));
	}

	public void write(SpringBeans beans, List<FilteredOutput> outputs) throws Exception
	{
		BeansNode beansNode = writeBeans(beans);
		write(beansNode, outputs);
	}

	private BeansNode writeBeans(SpringBeans beans) throws NoSuitableBeanWritterFormatException
	{
		BeansWriterContext bswc = new BeansWriterContext(beans, _beanPropertySelectionStrategy, _valueFormaterFactory);

		for (Object bean : beans.getBeans())
		{
			NodeCreatorContext ctx = new NodeCreatorContextImpl(bswc);
			ctx.addTopBeanNode(bean);
		}

		return bswc.getResolvedBeansNode();
	}
}
