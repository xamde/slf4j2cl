package org.slf4j.j2cl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class J2clLoggerFactory implements ILoggerFactory {

    private static final J2clLoggerFactory INSTANCE = new J2clLoggerFactory();
    private Map<String, J2clLogger> loggers = new HashMap<>();

    public static J2clLoggerFactory getInstance() {
        return INSTANCE;
    }

    private J2clLoggerFactory() {
        conf.put("", Level.INFO);
    }

    @Override
    public Logger getLogger(String name) {
        // TODO customize log levels;
        return loggers.computeIfAbsent(name, key -> new J2clLogger(getConfiguredLevel(name)));
    }

    private TreeMap<String,Level> conf = new java.util.TreeMap<>();

    /**
     *
     * @param path can be a package name or a fully qualified classname
     * @param slf4jLevel
     */
    public void setLogLevel( String path, Level slf4jLevel) {
        Level prev = conf.put(path, slf4jLevel);
        if(prev==null && slf4jLevel==null) {
            // same level, NO-OP
            return;
        }
        if(prev!=null && slf4jLevel != null && prev.toInt()==slf4jLevel.toInt()) {
            // same level, NO-OP
            return;
        }
        // reconfigure loggers
        for(Map.Entry<String, J2clLogger> entry : loggers.entrySet()) {
            String name = entry.getKey();
            J2clLogger logger = entry.getValue();
            Level level = getConfiguredLevel(name);
            logger.setLevel(level);
        }

    }

    public void dumpLogConf() {
        Logger logger = new J2clLogger(Level.INFO);
        logger.info("== Logger Configuration");
        conf.entrySet().forEach( entry -> logger.info("Path: '" + entry.getKey()+
                "' -> Level: "+entry.getValue().toString()));

        loggers.entrySet().forEach( entry -> logger.info("Name: '" + entry.getKey()+
                "' -> Level: "+entry.getValue().getLevel()));
    }

    // all loggers "under" path, but not already configured more specific
    // aaa    WARN
    // aaabbb DEBUG
    // aaaccc
    // aaaddd
    // aaaeee INFO
    // aaafff <- lookup
    // bbb
    private Level getConfiguredLevel(String name) {
        List<String> configuredKeys = new ArrayList<>( conf.headMap(name, true).keySet() );
        for (int i = configuredKeys.size()-1; i >= 0; i--) {
            String key = configuredKeys.get(i);
            if(name.startsWith(key)) {
                // use it
                return conf.get(key);
            }
        }
        throw new AssertionError("conf should contains a ROOT setting for ''");
    }

    public static void main(String[] args) {
        J2clLoggerFactory fac = J2clLoggerFactory.getInstance();
        fac.dumpLogConf();
        String n1 = "aaa.bbb.CCC";
        String n2 ="aaa.bbb.DDD";
        String n3 ="aaa.eee.ffff.GGG";
        String n4="hhh.iii.JJJ";
        Logger l1 = fac.getLogger(n1);
        Logger l2 = fac.getLogger(n2);
        Logger l3 = fac.getLogger(n3);
        Logger l4 = fac.getLogger(n4);

        l1.debug("-l1 debug "+n1);
        l2.debug("-l2 debug "+n2);
        l3.debug("-l3 debug "+n3);
        l4.debug("-l4 debug "+n4);
        l1.info("+l1 info "+n1);
        l2.info("+l2 info "+n2);
        l3.info("+l3 info "+n3);
        l4.info("+l4 info "+n4);
        l1.warn("+l1 warn "+n1);
        l2.warn("+l2 warn "+n2);
        l3.warn("+l3 warn "+n3);
        l4.warn("+l4 warn "+n4);

        fac.setLogLevel("", Level.WARN);
        fac.setLogLevel("aaa.bbb", Level.INFO);
        fac.dumpLogConf();
        l1.debug("-l1 debug "+n1);
        l2.debug("-l2 debug "+n2);
        l3.debug("-l3 debug "+n3);
        l4.debug("-l4 debug "+n4);
        l1.info("+l1 info "+n1);
        l2.info("+l2 info "+n2);
        l3.info("-l3 info "+n3);
        l4.info("-l4 info "+n4);
        l1.warn("+l1 warn "+n1);
        l2.warn("+l2 warn "+n2);
        l3.warn("+l3 warn "+n3);
        l4.warn("+l4 warn "+n4);

        fac.setLogLevel("hhh", Level.DEBUG);
        fac.dumpLogConf();
        l1.debug("-l1 debug "+n1);
        l2.debug("-l2 debug "+n2);
        l3.debug("-l3 debug "+n3);
        l4.debug("+l4 debug "+n4);
        l1.info("+l1 info "+n1);
        l2.info("+l2 info "+n2);
        l3.info("-l3 info "+n3);
        l4.info("+l4 info "+n4);
        l1.warn("+l1 warn "+n1);
        l2.warn("+l2 warn "+n2);
        l3.warn("+l3 warn "+n3);
        l4.warn("+l4 warn "+n4);
    }
}
