package ir.co.bayan.simorq.zal.extractor.nutch;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import ir.co.bayan.simorq.zal.extractor.nutch.ExtractorIndexingFilter;

import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.indexer.IndexingException;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.metadata.Metadata;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
public class ExtractorIndexingFilterTest {

	private ExtractorIndexingFilter filter;

	@Before
	public void setUp() throws Exception {
		filter = new ExtractorIndexingFilter();
		Configuration configuration = mock(Configuration.class);
		InputStream confStream = getClass().getResourceAsStream("/extractors-index-test.xml");
		when(configuration.getConfResourceAsInputStream(anyString())).thenReturn(confStream);
		filter.setConf(configuration);
	}

	@Test
	public void testFilter() throws IndexingException {
		NutchDocument doc = mock(NutchDocument.class);
		Metadata metadata = mock(Metadata.class);
		when(metadata.getValues("f1")).thenReturn(new String[] { "t1" });

		filter.addFieldsToDoc(doc, metadata);
		verify(doc).add("f1", "Hello t1");
	}
}
