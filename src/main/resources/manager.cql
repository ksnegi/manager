CREATE KEYSPACE IF NOT EXISTS manager
WITH
  replication = {'class': 'NetworkTopologyStrategy', 'datacenter1': 1} AND
  durable_writes = 'true';

CREATE TABLE IF NOT EXISTS manager.User (
  id text,
  email text,
  fullName text,
  managerId text,
  department text,
  PRIMARY KEY (id)
) WITH
  bloom_filter_fp_chance=0.001000 AND
  caching={'KEYS_ONLY' AND
  comment='' AND
  dclocal_read_repair_chance=0.100000 AND
  gc_grace_seconds=10800 AND
  read_repair_chance=0.050000 AND
  default_time_to_live=0 AND
  speculative_retry='99.0PERCENTILE' AND
  memtable_flush_period_in_ms=0 AND
  compaction={'class': 'LeveledCompactionStrategy'} AND
  compression={'sstable_compression': 'LZ4Compressor'};