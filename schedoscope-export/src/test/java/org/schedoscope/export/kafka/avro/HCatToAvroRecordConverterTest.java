/**
 * Copyright 2016 Otto (GmbH & Co KG)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.schedoscope.export.kafka.avro;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.apache.avro.generic.GenericRecord;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.junit.Before;
import org.junit.Test;
import org.schedoscope.export.HiveUnitBaseTest;

public class HCatToAvroRecordConverterTest extends HiveUnitBaseTest {

	HCatToAvroRecordConverter converter;

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
	}

	@Test
	public void testMapConverter() throws Exception {

		setUpHiveServer("src/test/resources/test_map_data.txt", "src/test/resources/test_map.hql", "test_map");
		converter = new HCatToAvroRecordConverter();
		Iterator<HCatRecord> it = hcatRecordReader.read();
		while (it.hasNext()) {
			HCatRecord record = it.next();
			GenericRecord rec = HCatToAvroRecordConverter.convertRecord(record, hcatInputSchema, "MyRecord");
			assertNotNull(rec);
		}
	}

	@Test
	public void testArrayConverter() throws Exception {

		setUpHiveServer("src/test/resources/test_array_data.txt", "src/test/resources/test_array.hql", "test_array");
		Iterator<HCatRecord> it = hcatRecordReader.read();
		while (it.hasNext()) {
			HCatRecord record = it.next();
			GenericRecord rec = HCatToAvroRecordConverter.convertRecord(record, hcatInputSchema, "MyRecord");
			assertNotNull(rec);
		}
	}

	@Test
	public void testStructConverter() throws Exception {

		setUpHiveServer("src/test/resources/test_struct_data.txt", "src/test/resources/test_struct.hql", "test_struct");
		Iterator<HCatRecord> it = hcatRecordReader.read();
		while (it.hasNext()) {
			HCatRecord record = it.next();
			GenericRecord rec = HCatToAvroRecordConverter.convertRecord(record, hcatInputSchema, "MyRecord");
			assertNotNull(rec);
		}
	}

	@Test
	public void testMapMapConverter() throws Exception {

		setUpHiveServer("src/test/resources/test_mapmap_data.txt", "src/test/resources/test_mapmap.hql", "test_mapmap");
		Iterator<HCatRecord> it = hcatRecordReader.read();
		while (it.hasNext()) {
			HCatRecord record = it.next();
			GenericRecord rec = HCatToAvroRecordConverter.convertRecord(record, hcatInputSchema, "MyRecord");
			assertNotNull(rec);
		}
	}

	@Test
	public void testStructStructConverter() throws Exception {

		setUpHiveServer("src/test/resources/test_structstruct_data.txt", "src/test/resources/test_structstruct.hql",
				"test_structstruct");
		Iterator<HCatRecord> it = hcatRecordReader.read();
		while (it.hasNext()) {
			HCatRecord record = it.next();
			GenericRecord rec = HCatToAvroRecordConverter.convertRecord(record, hcatInputSchema, "MyRecord");
			assertNotNull(rec);
		}
	}
}
