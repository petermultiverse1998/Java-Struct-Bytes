package struct_bytes;

public class DataType{
    private final String type;
    private final int eachSize;
    private final int count;
    private final Object value;

    DataType(String type, int eachSize, int count, Object value) {
        this.type = type;
        this.count = count;
        this.eachSize = eachSize;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public Object getValue() {
        return value;
    }

    public int getEachSize() {
        return eachSize;
    }

    @Override
    public String toString() {
        return String.format("%3d x [%dB] %s ",count,eachSize,type);
    }
}