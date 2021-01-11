package homework.excercise.three;

public class StringTransformerThreadImplTest extends StringsTransformerBaseTest{

	@Override
	public Class<? extends StringsTransformer> getSpecificImplClass() {
		return StringTransformerThreadImpl.class;
	}
}
