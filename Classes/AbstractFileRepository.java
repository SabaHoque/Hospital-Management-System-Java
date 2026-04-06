package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileRepository<T> {
    protected final String filePath;
    protected final List<T> items = new ArrayList<>();

    protected AbstractFileRepository(String filePath) {
        this.filePath = filePath;
    }

    protected abstract void loadFromFile();
    protected abstract String buildBlock(T item);
    protected abstract boolean blocksMatch(T item, String block);

    public void add(T item) {
        items.add(item);
        appendToFile(item);
    }

    private void appendToFile(T item) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(buildBlock(item));
        } catch (IOException ex) {
            throw new RuntimeException("Error saving to file", ex);
        }
    }

    public void delete(T item) {
        items.remove(item);
        rewriteFileExcluding(buildBlock(item));
    }

    public void update(String oldBlock, T updatedItem) {
        rewriteFileReplacing(oldBlock, buildBlock(updatedItem));
    }

    private void rewriteFileExcluding(String blockToRemove) {
        rewriteFile(blockToRemove, null);
    }

    private void rewriteFileReplacing(String oldBlock, String newBlock) {
        rewriteFile(oldBlock, newBlock);
    }

    private void rewriteFile(String targetBlock, String replacement) {
        try {
            List<String> lines = readAllLines();
            StringBuilder newContent = new StringBuilder();
            StringBuilder current = new StringBuilder();

            for (String line : lines) {
                current.append(line).append("\n");
                if (current.toString().endsWith("\n\n")) {
                    String block = current.toString();
                    if (block.equals(targetBlock)) {
                        if (replacement != null) newContent.append(replacement);
                    } else {
                        newContent.append(block);
                    }
                    current = new StringBuilder();
                }
            }
            try (FileWriter fw = new FileWriter(filePath)) {
                fw.write(newContent.toString());
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error rewriting file", ex);
        }
    }

    protected List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public int searchByKey(String key, java.util.function.Function<T, String> keyExtractor) {
        for (int i = 0; i < items.size(); i++) {
            if (keyExtractor.apply(items.get(i)).equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= items.size()) return null;
        return items.get(index);
    }

    // NEW PUBLIC METHOD - This fixes the "items has protected access" error
    public int getAllItemsSize() {
        return items.size();
    }
}