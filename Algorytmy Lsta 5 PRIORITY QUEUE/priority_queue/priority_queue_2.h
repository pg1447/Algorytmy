#ifndef PRIORYTY_QUEUE2_H
#define PRIORYTY_QUEUE2_H
template <class item>
class Priority_queue2{
private:
	item** queue;
	int last;
	int size;
	int d;
	int parentIndex(int index);
	void heapify(int index);
public:
	Priority_queue2<item>(int size, int d) : queue(new item*[size]), size(size), last(-1), d(d){};
	bool insert(item *ptr);
	item* extractMin();
	bool decreaseKey(item* ptr, double value);
	int actualSize();
	~Priority_queue2();
};

template <typename item>
bool Priority_queue2<item>::insert(item* ptr){
	if (last == size - 1)
		return false;
	++last;
	queue[last] = ptr;
	queue[last]->position = last;
	int actual = last;
	int parent = parentIndex(last);
	while (actual > 0 && *queue[actual] < *queue[parent]){
		int oldpos = queue[parent]->position;
		queue[parent]->position = queue[actual]->position;
		queue[actual]->position = oldpos;
		item* temp = queue[parent];
		queue[parent] = queue[actual];
		queue[actual] = temp;
		actual = parent;
		parent = parentIndex(actual);
	}
	return true;
}

template<typename item>
int Priority_queue2<item>::parentIndex(int index){
	return (index - 1) / d;
}



template<typename item>
item* Priority_queue2<item>::extractMin(){
	if (last < 0) return NULL;
	item* temp = queue[0];
	queue[0] = queue[last];
	queue[0]->position = 0;
	--last;
	heapify(0);
	return temp;
}

template<typename item>
void Priority_queue2<item>::heapify(int index){
	int l = (index*d) + 1;
	int smallest = index;
	for (int j = l; j<l + d && j <= last; j++)
	if (*queue[j]<*queue[smallest])
		smallest = j;
	if (smallest != index){
		int oldpos = queue[index]->position;
		queue[index]->position = queue[smallest]->position;
		queue[smallest]->position = oldpos;
		item *temp = queue[index];
		queue[index] = queue[smallest];
		queue[smallest] = temp;
		heapify(smallest);
	}
}

template<typename item>
bool Priority_queue2<item>::decreaseKey(item* ptr, double value){
	if (ptr->value < value)
		return false;
	queue[ptr->position]->value = value;
	int actual = ptr->position;
	int parent = parentIndex(actual);
	while (actual > 0 && *queue[actual] < *queue[parent]){
		int oldpos = queue[parent]->position;
		queue[parent]->position = queue[actual]->position;
		queue[actual]->position = oldpos;
		item *temp = queue[parent];
		queue[parent] = queue[actual];
		queue[actual] = temp;
		actual = parent;
		parent = parentIndex(actual);
	}
	return true;
}
template<typename item>
int Priority_queue2<item>::actualSize(){
	return last + 1;
}

template<typename item>
Priority_queue2<item>::~Priority_queue2(){
	delete[] queue;
}

#endif