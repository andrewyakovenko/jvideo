package org.jvideo.memory;

public class Buffers {
    private Buffers() {}

    public static BufferSource naive() {
        return new NaiveBufferSource();
    }

    public static BufferSource pool() {
        return new BufferPool();
    }

    public static BufferSource pool(int maxSize) {
        return new BufferPool(maxSize);
    }

}
