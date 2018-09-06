/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.utiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MeDateConverter implements Converter {

	private String dateFormat;

	public MeDateConverter(String dateFormat) {
		super();
		this.dateFormat = dateFormat;
	}

	public boolean canConvert(Class clazz) {
		return Date.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

		Date fecha = (Date) value;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		writer.setValue(formatter.format(fecha));
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		Date date = new Date();
		try {
			date = new SimpleDateFormat(dateFormat).parse(reader.getValue());
		} catch (ParseException e) {
			throw new ConversionException(e.getMessage(), e);
		}
		return date;
	}
}