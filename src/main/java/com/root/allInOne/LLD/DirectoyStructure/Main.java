package com.root.allInOne.LLD.DirectoyStructure;


/*
*
* Input directory:
```
{
  "home": {
    "asdf.pdf": "pdf_str_content",
    "users": {
      "user1": {
        "profile.txt": "John Doe, 30, john.doe@example.com"
      },
      "user2": {
        "profile.txt": "Jane Smith, 25, jane.smith@example.com"
      }
    },
    "photo_1.jpg": "photo_content",
    "docs": {
      "file1.txt": "This is a text file content.",
      "file2.docx": "Sample Word document content."
    },
    "photos": {
      "image1.png": "image_content_1",
      "image2.jpg": "image_content_2"
    }
  }
}

* */


public class Main {

    //autowire

    public static void main(String[] args) {
        Directory directory = new Directory();
        directory.init();

        //directory.ls("/home/users");
        directory.cp("/home/asdf.pdf" , "/home/users/user2");
        directory.ls("/home/users/user2");

    }
}
