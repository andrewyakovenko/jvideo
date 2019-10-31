package org.jvideo.memory;

import java.nio.ByteBuffer;

public interface BufferSource {

    ByteBuffer get(int size);

    void recycle(ByteBuffer buffer);

}
