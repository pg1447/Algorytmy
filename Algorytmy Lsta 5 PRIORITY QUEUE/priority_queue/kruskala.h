#ifndef KRUSKALA_H
#define KRUSKALA_H
#include "graph.h"
#include "priority_queue.h"

namespace Algo{
	class Kruskala{
	private:
		class Node{
		public:
			int rank;
			Node *parent;
			Node() :rank(0), parent(this) {};
		};

		static Node* find(Node *x);
		static void unio(Node *x, Node *y);
	public:
		static Graph* generate(Graph &graph);
	};

	Graph* Kruskala::generate(Graph &graph){
		int nedge = graph.getNedge(), nvertex = graph.getNvertex();
		int d = nedge / nvertex;
		Priority_queue<Edge> *que = new Priority_queue<Edge>(nedge, d);
		Graph *temp = new Graph(nvertex);
		Node **nodes = new Node*[nvertex];
		for (int i = 0; i < nvertex; ++i){
			nodes[i] = new Node();
			Edge* ptr = graph.getEdge(i);
			while (ptr != NULL){
				que->insert(*ptr);
				ptr = ptr->next;
			}
		}

		Edge *ptr = &que->extractMin();

		while (temp->getNedge()<nvertex - 1){
			Node *u = find(nodes[ptr->start]), *v = find(nodes[ptr->end]);
			if (u != v){
				temp->addDirectedEdge(ptr->start, ptr->end, ptr->weight);
				unio(u, v);
			}
			if (que->actualSize() == 0)
				break;
			ptr = &que->extractMin();
		}
		for (int i = 0; i < nvertex; ++i)
			delete nodes[i];
		delete[] nodes;
		delete que;
		return temp;
	}


	Kruskala::Node* Kruskala::find(Node *x){
	while (x != x->parent)
			x = x->parent;
		return x;
	}
	void Kruskala::unio(Node *x, Node *y){
		Node* idx = find(x);
		Node* idy = find(y);
		if (idx->rank > idy->rank)
			idy->parent = idx;
		else
			idx->parent = idy;
		if (idx->rank == idy->rank)
			idy->rank++;
	}
}
#endif