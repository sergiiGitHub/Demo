 public static class MinHeap {

        private int items[];
        private int size;

        public MinHeap(int capcacity) {
            items = new int[capcacity];
        }

        private int getLeftChildrenIndex(int parentIndex) {
            return parentIndex*2 + 1;
        }

        private int getRightChildrenIndex(int parentIndex) {
            return parentIndex*2 + 2;
        }

        private int getParentIndex(int childrenIndex) {
            return (childrenIndex - 1) / 2;
        }

        private boolean hasLeftChildrenIndex(int parentIndex) {
            return getLeftChildrenIndex(parentIndex) < size;
        }

        private boolean hasRightChildrenIndex(int parentIndex) {
            return getRightChildrenIndex(parentIndex) < size;
        }

        private boolean hasParentIndex(int childrenIndex) {
            return getParentIndex(childrenIndex) >= 0;
        }

        private int getLeftChild(int parentIndex) {
            return items[getLeftChildrenIndex(parentIndex)];
        }s

        private int getRightChildren(int parentIndex) {
            return items[getRightChildrenIndex(parentIndex)];
        }

        private int getParent(int childrenIndex) {
            return items[getParentIndex(childrenIndex)];
        }

        private void swap(int lIndex, int rIndex) {
            int buf = items[lIndex];
            items[lIndex] = items[rIndex];
            items[rIndex] = buf;
        }

        private void heapifyDown(int index) {
            while (hasLeftChildrenIndex(index)) {
                int smollerChikdIndex = getLeftChildrenIndex(index);
                if (hasRightChildrenIndex(index) && getRightChildren(index) < getLeftChild(index)) {
                    smollerChikdIndex = getRightChildrenIndex(index);
                }

                if (items[index] < items[smollerChikdIndex]) {
                    break;
                } else {
                    swap(index, smollerChikdIndex);
                }
                index = smollerChikdIndex;
            }       
        }

        private void heapifyUp() {
            int index = size - 1;
            while (hasParentIndex(index) && getParent(index) > items[index]) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }       
        }

        public int peek(){
            return items[0];
        }

        public int pop(){
            int item = items[0];
            --size;
            items[0] = items[size];

            heapifyDown(0);

            return item;
        }

        public void add(int item){
            items[size] = item;
            ++size;
            heapifyUp();
        }
        
        public int remove(int value) {
            for (int i = 0; i < size; ++i) {
                if (items[i] == value) {
                    int item = items[i];
                    --size;
                    items[i] = items[size];

                    heapifyDown(i);
                    return item;
                }
            }
            return -1;
        }

    }