package org.SBPSWar.interceptor;

import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.Attribute;

public class TestViewPreparer implements ViewPreparer {

    public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext)
    throws PreparerException {
    	Attribute attr = new Attribute();
 
        attributeContext.putAttribute(
            "body",
            new Attribute("This is the value added by the ViewPreparer"));
    }
}
