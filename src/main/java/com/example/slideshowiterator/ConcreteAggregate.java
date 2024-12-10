package com.example.slideshowiterator;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {

    private String filetop;
    private List<File> imagesList;

    public ConcreteAggregate(String filetop) {
        this.filetop = filetop;
        this.imagesList = new ArrayList<>();
        loadImages(new File(filetop));
    }

    private void loadImages(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    loadImages(file);
                } else if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
                    imagesList.add(file);
                }
            }
        }
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator();
    }

    public List<File> getImagesList() {
        return imagesList;
    }

    private class ImageIterator implements Iterator {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < imagesList.size();
        }

        @Override
        public Image next() {
            File file = imagesList.get(current);
            current++;
            try {
                return new Image(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
