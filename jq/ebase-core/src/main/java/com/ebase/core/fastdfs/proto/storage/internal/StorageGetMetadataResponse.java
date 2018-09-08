package com.ebase.core.fastdfs.proto.storage.internal;

import com.ebase.core.fastdfs.domain.MateData;
import com.ebase.core.fastdfs.proto.FdfsResponse;
import com.ebase.core.fastdfs.proto.mapper.MetadataMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * 列出分组信息执行结果
 * 
 * @author tobato
 *
 */
public class StorageGetMetadataResponse extends FdfsResponse<Set<MateData>> {

    /**
     * 解析反馈内容
     */
    @Override
    public Set<MateData> decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        if (contentSize != getContentLength()) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }
        return MetadataMapper.fromByte(bytes, charset);

    }

}
