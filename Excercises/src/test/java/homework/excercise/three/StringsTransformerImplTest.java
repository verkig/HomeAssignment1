package homework.excercise.three;

import org.junit.Ignore;

@Ignore
public class StringsTransformerImplTest extends StringsTransformerBaseTest {

	@Override
	public Class<? extends StringsTransformer> getSpecificImplClass() {
		return StringsTransformerImpl.class;
	}

}
