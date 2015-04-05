package processor.provider;

import constants.Constants;

public class AnaglyphProvider {

    public AnaglyphBuilder getAnaglyphBuilder(Constants type) {

        return new AnaglyphBuilderImpl(type);

    }
}
