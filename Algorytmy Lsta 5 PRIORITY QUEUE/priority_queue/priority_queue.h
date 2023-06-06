#ifndef PRIORYTY_QUEUE_H
#define PRIORYTY_QUEUE_H
template <class item>
class Priority_queue{
private:
	item* queue;
	int last;
	int size;
	int d;
	int parentIndex(int index);
	void heapify(int index);
public:
	Priority_queue<item>(int size, int d) : queue(new item[size]), size(size), last(-1), d(d){};
	bool insert(item ptr);
	item extractMin();
	bool decreaseKey(int index, item ptr);
	int actualSize();
	~Priority_queue();
};

template <typename item>
bool Priority_queue<item>::insert(item ptr){
	if (last == size - 1)
		return false;
	++last;
	queue[last] = ptr;
	int actual = last;
	int parent = parentIndex(last);
	while (actual > 0 && queue[actual] < queue[parent]){
		item temp = queue[parent];
		queue[parent] = queue[actual];
		queue[actual] = temp;
		actual = parent;
		parent = parentIndex(actual);
	}
	return true;
}

template<typename item>
int Priority_queue<item>::parentIndex(int index){
	return (index - 1) / d;
}



template<typename item>
item Priority_queue<item>::extractMin(){
	if (last < 0) return queue[0];
	item temp = queue[0];
	queue[0] = queue[last];
	--last;
	heapify(0);
	return temp;
}

template<typename item>
void Priority_queue<item>::heapify(int index){
	int l = (index*d) + 1;
	int smallest = index;
	for (int j = l; j<l + d && j<=last; j++) 
	if (queue[j]<queue[smallest])
		smallest = j;
	if (smallest != index){  
		item temp = queue[index];
		queue[index] = queue[smallest];
		queue[smallest] = temp;
		heapify(smallest);
	}
}

template<typename item>
bool Priority_queue<item>::decreaseKey(int index, item ptr){
	if (index > last || queue[index] < ptr)
		return false;
	queue[index] = ptr;
	int actual = index;
	int parent = parentIndex(actual);
	while (actual > 0 && queue[actual] < queue[parent]){
		item temp = queue[parent];
		queue[parent] = queue[actual];
		queue[actual] = temp;
		actual = parent;
		parent = parentIndex(actual);
	}
	return true;
}
template<typename item>
int Priority_queue<item>::actualSize(){
	return last + 1;
}

template<typename item>
Priority_queue<item>::~Priority_queue(){
	delete[] queue;
}
#endif