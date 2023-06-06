#ifndef EDGE_H
#define EDGE_H
class Edge{
public:
	int start, end;
	double weight;
	Edge* next;

	Edge() : next(NULL){};
	Edge(int start, int end, double weight) :start(start), end(end), weight(weight), next(NULL){};
	bool operator < (const Edge& arg)const {
		return weight < arg.weight;
	}
};
#endif