package com.ebay.sd.jbehave;

import java.io.IOException;

/**
 * Created by mmerhav on 20/3/2017.
 */
public interface StoriesEnumerator {

    void enumerateStory(String path, int startFrom) throws IOException;
}
