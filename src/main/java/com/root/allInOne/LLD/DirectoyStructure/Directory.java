package com.root.allInOne.LLD.DirectoyStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//leetcode vala question aise kiya -> aur ye hi follow karna hai bossss
//class Directory {
//    Map<String, Directory> children = new HashMap<String, Directory>();
//    Set<String> files = new HashSet<String>();
//}
//
//class FileSystem {
//    Directory root = new Directory();
//    Map<String, String> fileContent = new HashMap<String, String>();
//
//    public List<String> ls(String path) {
//        String[] paths = path.split("/");
//        Directory node = root;
//        for(int i=1; i<paths.length; i++) {
//            if(node.children.get(paths[i])==null) return Arrays.asList(paths[i]);
//            node = node.children.get(paths[i]);
//        }
//        List<String> ans = new ArrayList(node.files);
//        for(String key: node.children.keySet()) ans.add(key);
//        Collections.sort(ans);
//        return ans;
//    }
//
//    public void mkdir(String path) {
//        String[] paths = path.split("/"); int len = paths.length;
//        Directory node = root;
//        for(int i=1; i<len; i++) {
//            if(node.children.get(paths[i]) == null) node.children.put(paths[i], new Directory());
//            node = node.children.get(paths[i]);
//        }
//    }
//
//    public void addContentToFile(String path, String content) {
//        String[] paths = path.split("/"); int len = paths.length;
//        Directory node = root;
//        for(int i=1; i<len-1; i++) {
//            if(node.children.get(paths[i]) == null) node.children.put(paths[i], new Directory());
//            node = node.children.get(paths[i]);
//        }
//        node.files.add(paths[len-1]);
//        fileContent.put(path, fileContent.getOrDefault(path,"")+content);
//    }
//
//    public String readContentFromFile(String filePath) {
//        return fileContent.get(filePath);
//    }
//}

//interview ke vakt ye banaya tha


//class Contents {
//   public Map<String, String> files = new HashMap<String, String>();
//    public Set<String> directorPaths = new HashSet<>();}

public class Directory {

    Map<String, Contents>  directory = new HashMap<>();
    String pwd = "/home";
    //pwd  = path

    // cp /home/users/user1/profile.txt

    //cp("/home/asdf.pdf" , "/home/users/user2")
    void cp(String pathToSourceFile, String destinationPath) {

        if(!pathToSourceFile.startsWith("/")) {
            pathToSourceFile = pwd + "/" + pathToSourceFile;
        }

        if(!destinationPath.startsWith("/")) {
            destinationPath = pwd + "/" + destinationPath;
        }

        int len = pathToSourceFile.length();
        int lastIndex = pathToSourceFile.lastIndexOf('/');
        String directoryPath = pathToSourceFile.substring(0, lastIndex);
        String fileName = pathToSourceFile.substring(lastIndex+1, len);
        System.out.println("fileName = " + fileName);
        System.out.println("directoryPath = " + directoryPath);
        String content = directory.get(directoryPath).files.get(fileName);
        System.out.println("content = " + content);
        directory.get(destinationPath).files.put(fileName, content);

    }

    void ls(String path) {
        if(path==null || path.length()==0) return;
        if(!path.startsWith("/")) {
            path = pwd + "/" + path;
        }

        Contents contents = directory.get(path);

        for(Map.Entry<String, String> file: contents.files.entrySet()) {
            System.out.println("fileName = " + file.getKey());
        }

        for(String d: contents.directorPaths) {
            System.out.println("directory = " + d);
        }
    }
    void mv(String pathToSourceFile, String destinationPath) {
        String[] dirs = pathToSourceFile.split("/");
        String fileName = dirs[dirs.length-1];
        String directoryOffile = "";
        for(int i=0; i< dirs.length-1; i++) {
            directoryOffile += "/" + dirs[i];
        }
        System.out.println("fileName = " + fileName);
        System.out.println("directoryOffile = " + directoryOffile);
        String content = directory.get(directoryOffile).files.get(fileName);
        directory.get(destinationPath).files.put(fileName, content);
        directory.get(directoryOffile).files.remove(fileName);
    }
   // void pwd();


    void init() {
        // Initialize the directory structure

        directory.put("/home", new Contents(
                Map.of(
                        "asdf.pdf", "pdf_str_content",
                        "photo_1.jpg", "photo_content"
                ),
                Set.of("/home/users", "/home/docs", "/home/photos")
        ));

        directory.put("/home/users", new Contents(
                Map.of(),
                Set.of("/home/users/user1", "/home/users/user2")
        ));

        directory.put("/home/users/user1", new Contents(
                Map.of(
                        "profile.txt", "John Doe, 30, john.doe@example.com"
                ),
                Set.of()
        ));
        Map<String, String> map = new HashMap<>();
        map.put("profile.txt", "Jane Smith, 25, jane.smith@example.com");
        directory.put("/home/users/user2", new Contents(
                map,
                Set.of()
        ));

        directory.put("/home/docs", new Contents(

                Map.of(
                        "file1.txt", "This is a text file content.",
                        "file2.docx", "Sample Word document content."
                ),
                Set.of()
        ));

        directory.put("/home/photos", new Contents(
                Map.of(
                        "image1.png", "image_content_1",
                        "image2.jpg", "image_content_2"
                ),
                Set.of()
        ));
    }





}
