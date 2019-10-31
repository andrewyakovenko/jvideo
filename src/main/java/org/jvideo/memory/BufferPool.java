package org.jvideo.memory;

import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

class BufferPool implements BufferSource {

    private int poolSize;
    private ConcurrentHashMap<Integer, Queue<ByteBuffer>> pools = new ConcurrentHashMap<>();

    /**
     * Create a buffer pool of maximum size {@code poolSize}.
     *
     * @param poolSize maximum size of the pool, set separately for each buffer capacity. Set to 0 for an unlimited pool.
     */
    BufferPool(int poolSize) {
        this.poolSize = poolSize;
    }

    /**
     * Create a buffer pool with unlimited size. Same as {@link BufferPool#BufferPool(int)} with zero size.
     */
    BufferPool() {
        this(0);
    }

    @Override
    public ByteBuffer get(int size) {
        Queue<ByteBuffer> pool = getPool(size);

        ByteBuffer buffer = pool.poll();
        return buffer != null ? buffer : ByteBuffer.allocateDirect(size);
    }

    @Override
    public void recycle(ByteBuffer buffer) {
        Queue<ByteBuffer> pool = getPool(buffer.capacity());
        pool.offer(buffer);
    }

    private Queue<ByteBuffer> getPool(int size) {
        return pools.computeIfAbsent(size, s -> new LinkedBlockingQueue<>(poolSize));
    }

}
