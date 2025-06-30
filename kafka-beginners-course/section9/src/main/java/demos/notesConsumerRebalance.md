# Consumer Groups and Partition Rebalance

Consumer groups and Partition Rebalance
- Moving partitions between consumers is called a "rebalance"
- Reassignment of partitions happens when a consumer leaves or joins a group
- It also happens if an administrator adds new partitions into a topic
Eager Rebalance
- All consumers stop, give up their membership of partitions
- They rejoin the consumer group and get a new partition assignment
- During a short period of time, the entire consumer group stops processing
- Consumers don't necessarily "get back" the same partitions as they used to
Cooperative Rebalance (Incremental Rebalance)
- Reassigning a small subset of the partitions from one consumer to another
- Other consumers that don't have reassigned partitions can still process uninterrupted
- Can go through sever iterations to find a "stable" assignment (hence "incremental")
- Avoids "stop-the-world" events where all consumers stop processing the data

## Cooperative Rebalance, how to use?
- Kafka Consumer: `partition.assignment.strategy`
  - RangeAssignor: assign partitions on a per-topic basis (can lead to rebalance)
  - RoundRobin: assign partitions across all topics in round-robin fashion, optimal balancer
  - StickAssignor: balanced like RoundRobin and then minimises partition movements when consumer join/ leave the group in order to minimise movements.
  - CooperativeStickyAssignor: rebalance strategy is identical to StickAssignor but supports cooperative rebalances and therefore consumers can keep on consuming from the topic
  - The default assignor is [RangeAssignor, CooperativeStickyAssignor] which will use the RangeAssignor by default, but allows upgrading to the CooperativeStickyAssignor with just a single rolling bounce that removes the RangeAssignor from the list.
- Kafka Connect: Already implemented (enabled by default)
- Kafka Streams: Turned on by default using ```StreamsParitionAssignore```

## Static Group Membership
- By default, when a consumer leaves a group its partitions are revoked and re-assigned.
- If it joins back, it will have a new "member ID" and new partitions assigned.
- If you specify `group.instance.id` it makes the consumer a static member.
- Upon leaving, the consumer has up to `session.timeout.ms` to join back and get back its partitions (else they will be re-assigned), without triggering a rebalance
- This is helpful when consumers maintain local state and cache (to re-building the cache)

## Kafka Consumer - Auto Offset Commit Behaviour
- In the Java Consumer API, offsets are regularly committed
- Enable at-least once reading scenario by default (under conditions)
- Offsets are committed when you call `.poll()` and `auto.commit.interval.ms` has elapsed
- Example:`auto.commit.interval.ms=5000` and `enable.auto.commit=true` will commit
- Make sure messages are all successfully processed before you call `poll()` again
  - If you don't, you wil not be in the at-least-once reading scenario
  - In that (rare) case, you must disable `enable.auto.commit` and most likely move processing to a separate thread and then from time-to-time call `.commitSync()` or `.commitAsync()` with the correct offsets manually (advanced)

