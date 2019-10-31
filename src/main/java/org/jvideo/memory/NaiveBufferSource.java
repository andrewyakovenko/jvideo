package org.jvideo.memory;

import java.nio.ByteBuffer;

class NaiveBufferSource implements BufferSource {
    @Override
    public ByteBuffer get(int size) {
        return ByteBuffer.allocate(size);
    }

    @Override
    public void recycle(ByteBuffer buffer) {
        // nothing to do
    }
}
