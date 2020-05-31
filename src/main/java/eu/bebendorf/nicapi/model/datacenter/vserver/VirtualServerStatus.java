package eu.bebendorf.nicapi.model.datacenter.vserver;

import com.google.gson.JsonObject;
import eu.bebendorf.nicapi.HttpClient;

import java.util.Map;

public class VirtualServerStatus {
    public int netin;
    public int cpu;
    public BallonInfo balloninfo;
    public int vmid;
    public int pid;
    public String template;
    public int netout;
    public long balloon;
    public long maxmem;
    public long uptime;
    public long freemem;
    public long diskwrite;
    public String qmpstatus;
    public HA ha;
    public long diskread;
    public String status;
    public long mem;
    public long maxdisk;
    private JsonObject blockstat;
    private JsonObject nics;
    public String name;
    public int agent;
    public int cpus;
    public int disk;
    public Map<String, BlockStatDisk> getBlockStat(){
        return HttpClient.toMap(blockstat, BlockStatDisk.class);
    }
    public Map<String, NicsInterface> getNics(){
        return HttpClient.toMap(nics, NicsInterface.class);
    }
    public static class BallonInfo {
        public long mem_swapped_in;
        public long major_page_faults;
        public long free_mem;
        public long actual;
        public long minor_page_faults;
        public long max_mem;
        public long total_mem;
        public long last_update;
        public long mem_swapped_out;
    }
    public static class NicsInterface {
        public long netin;
        public long netout;
    }
    public static class BlockStatDisk {
        public long rd_merged;
        public long unmap_total_time_ns;
        public long rd_bytes;
        public long wr_total_time_ns;
        public long idle_time_ns;
        public boolean account_invalid;
        public long invalid_flush_operations;
        public long wr_highest_offset;
        public long failed_wr_operations;
        public long unmap_operations;
        public long wr_operations;
        public long unmap_merged;
        public long failed_unmap_operations;
        public long unmap_bytes;
        public long flush_operations;
        public boolean account_failed;
        //timed_stats
        public long rd_operations;
        public long flush_total_time_ns;
        public long failed_flush_operations;
        public long rd_total_time_ns;
        public long wr_bytes;
        public long invalid_wr_operations;
        public long invalid_unmap_operations;
        public long wr_merged;
        public long invalid_rd_operations;
        public long failed_rd_operations;
    }
    public static class HA {
        public int managed;
    }
}
