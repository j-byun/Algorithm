import heapq
import sys

# n번째 큰 수 -> 우선순위 큐, 최소 힙
heap = []
n = int(sys.stdin.readline())
for i in range(n):
    arr = list(map(int, sys.stdin.readline().split()))
    for num in arr:
        if len(heap) < n:
            heapq.heappush(heap, num)
        else:
            if heap[0] < num:
                heapq.heappop(heap)
                heapq.heappush(heap, num)



# heapq.heapify(arr)
# heap.append(arr)
# arr = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
# heap = heapq.heapify(arr)
print(heap[0])

# for _ in range(n - 1):
#     heapq.heappop(heap)[1]
# print(heapq.heappop(heap)[1])

# for _ in range(n):
#     heapq.heappush()
