package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.XPP3Reader;
import org.xmlpull.v1.XmlPullParserException;

public class NoEnter {
	public static void main(String[] args) throws IOException, DocumentException, XmlPullParserException {
		BufferedReader br=new BufferedReader(new FileReader(new File("D:\\testKey.xml")));
		StringBuffer buffer=new StringBuffer();
		String line=null;
		while((line=br.readLine())!=null){
			buffer.append(line);
		}
		String xml=buffer.toString();
		Document userKey=new XPP3Reader().read(new File("D:\\testKey.xml"));
		@SuppressWarnings("unchecked")
		List<Element> list=(List<Element>)userKey.selectNodes("//question-body[@type='1']");
		System.out.println(list.get(0));
	}
}
