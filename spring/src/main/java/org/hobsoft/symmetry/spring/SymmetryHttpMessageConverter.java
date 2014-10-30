/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.symmetry.spring;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.hobsoft.symmetry.ui.Component;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.google.common.base.Charsets;

/**
 * Spring HTTP message converter for UI components.
 */
public class SymmetryHttpMessageConverter extends AbstractHttpMessageConverter<Component>
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public SymmetryHttpMessageConverter()
	{
		super(MediaType.TEXT_HTML);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// HttpMessageConverter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType)
	{
		return false;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// AbstractHttpMessageConverter methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected boolean supports(Class<?> clazz)
	{
		return Component.class.isAssignableFrom(clazz);
	}

	@Override
	protected Component readInternal(Class<? extends Component> clazz, HttpInputMessage inputMessage) throws IOException
	{
		throw new HttpMessageNotReadableException("Cannot read components");
	}

	@Override
	protected void writeInternal(Component component, HttpOutputMessage outputMessage) throws IOException
	{
		Writer writer = new OutputStreamWriter(outputMessage.getBody(), Charsets.UTF_8);
		writer.write("<html/>");
		writer.flush();
	}
}
