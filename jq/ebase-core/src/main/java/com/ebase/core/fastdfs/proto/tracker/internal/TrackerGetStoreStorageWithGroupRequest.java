package com.ebase.core.fastdfs.proto.tracker.internal;

import com.ebase.core.fastdfs.proto.CmdConstants;
import com.ebase.core.fastdfs.proto.FdfsRequest;
import com.ebase.core.fastdfs.proto.OtherConstants;
import com.ebase.core.fastdfs.proto.ProtoHead;
import com.ebase.core.fastdfs.proto.mapper.FdfsColumn;
import org.apache.commons.lang3.Validate;

/**
 * 按分组获取存储节点
 * 
 * @author tobato
 *
 */
public class TrackerGetStoreStorageWithGroupRequest extends FdfsRequest {

    private static final byte withGroupCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ONE;

    /**
     * 分组定义
     */
    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private final String groupName;

    /**
     * 获取存储节点
     * 
     * @param groupName
     */
    public TrackerGetStoreStorageWithGroupRequest(String groupName) {
        Validate.notBlank(groupName, "分组不能为空");
        this.groupName = groupName;
        this.head = new ProtoHead(withGroupCmd);
    }

    public String getGroupName() {
        return groupName;
    }

}
