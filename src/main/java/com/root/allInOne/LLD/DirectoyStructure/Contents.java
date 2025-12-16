package com.root.allInOne.LLD.DirectoyStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Contents {

    public Contents(Map<String, String> files, Set<String> directorPaths) {
        this.files = files;
        this.directorPaths = directorPaths;
    }
    public Map<String, String> files = new HashMap<String, String>();
    public Set<String> directorPaths = new HashSet<>();
}
