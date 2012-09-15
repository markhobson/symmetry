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
package org.hobsoft.symmetry.state;

import java.beans.PropertyDescriptor;

/**
 * 
 * 
 * @author Mark Hobson
 */
public interface StateCodec
{
	EncodedState encodeState(State state) throws StateException;
	
	State decodeState(EncodedState encodedState) throws StateException;
	
	String encodeBean(Object bean) throws StateException;
	
	Object decodeBean(String encodedBean) throws StateException;
	
	String encodePropertyValue(PropertyState property) throws StateException;
	
	Object decodePropertyValue(Object bean, PropertyDescriptor descriptor, String encodedValue) throws StateException;
}
