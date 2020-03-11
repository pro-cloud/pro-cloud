package com.cloud.generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.WordUtils;
import org.junit.Test;

@Slf4j
public class MyTest {


    @Test
    public void aa(){
        String replace = WordUtils.capitalizeFully("aijm_ai", new char[]{'_'}).replace("_", "");
        log.info(replace);
    }
}
