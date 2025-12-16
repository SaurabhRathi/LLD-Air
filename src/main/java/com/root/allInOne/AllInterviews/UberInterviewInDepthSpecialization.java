package com.root.allInOne.AllInterviews;


import java.util.*;
/*
this was uber interview - In depth specialization round
*/

class VersionMetaData {
    public int minCompatibleVersion;
    public int maxCompatibleVersion;

    public VersionMetaData(int minCompatibleVersion, int maxCompatibleVersion) {
        this.minCompatibleVersion = minCompatibleVersion;
        this.maxCompatibleVersion = maxCompatibleVersion;
    }
}


class VersionManager {

    Integer latestVersion = null;
    Map<Integer, VersionMetaData> versionCompatibilityMap = new HashMap<Integer, VersionMetaData>();
    public void addNewVersion(int ver, boolean isCompatibleWithPrev) {

        if(latestVersion==null) {
            versionCompatibilityMap.put(ver, new VersionMetaData(-1, -1));

        } else {
            if(!isCompatibleWithPrev) {
                versionCompatibilityMap.put(ver, new VersionMetaData(-1, -1));
            } else {
                VersionMetaData prevVersionData = versionCompatibilityMap.get(latestVersion);

                if(prevVersionData.minCompatibleVersion==-1) {
                    versionCompatibilityMap.put(ver, new VersionMetaData(latestVersion, latestVersion));
                } else {
                    versionCompatibilityMap.put(ver, new VersionMetaData(prevVersionData.minCompatibleVersion, latestVersion));
                }
            }
        }
        latestVersion = ver;
    }




    public boolean isCompatible(int srcVer, int targetVer) {


        if(versionCompatibilityMap.get(srcVer)==null) return false;
        if(versionCompatibilityMap.get(targetVer)==null) return false;

        if(srcVer==targetVer) return true;



        int greaterVersion = srcVer > targetVer ? srcVer: targetVer;
        int lowerVersion = srcVer < targetVer ? srcVer: targetVer;

        VersionMetaData metaData = versionCompatibilityMap.get(greaterVersion);

        if(metaData.minCompatibleVersion==-1) return false;

        if(metaData.minCompatibleVersion<=lowerVersion && lowerVersion<=metaData.maxCompatibleVersion) {
            return true;
        }

        return false;

    }
}

public class UberInterviewInDepthSpecialization {
    public static void main(String[] args) {
        VersionManager versionManager = new VersionManager();
        versionManager.addNewVersion(1,false);
        versionManager.addNewVersion(2,true);
        versionManager.addNewVersion(3,true);
        versionManager.addNewVersion(4,true);
        versionManager.addNewVersion(5,true);
        versionManager.addNewVersion(7,false);
        System.out.println(versionManager.isCompatible(1,7));
        System.out.println(versionManager.isCompatible(1,6));
        System.out.println(versionManager.isCompatible(100,7));
        System.out.println(versionManager.isCompatible(2,5));
    }
}
