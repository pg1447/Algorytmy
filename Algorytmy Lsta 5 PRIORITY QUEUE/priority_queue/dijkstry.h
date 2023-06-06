#ifndef DIJKSTY_H
#define DIJKSTY_H
#include "graph.h"
#include "priority_queue_2.h"

namespace Algo{
	class Dijksty{
	private:
		class Node{
		public:
			int id;
			double value;
			int position;
			Node *way;
			Node(int i) :value(10000.0), way(NULL), id(i){};
			bool operator < (const Node& arg)const {
				return value < arg.value;
			}
		};
	public:
		static void generate(Graph &graph, int start);
		
	};


	void Dijksty::generate(Graph &graph, int start){
		int nedge = graph.getNedge(), nvertex = graph.getNvertex();
		Priority_queue2<Node>* que = new Priority_queue2<Node>(nvertex, 2);
		Node** nodes = new Node*[nvertex];
		
		for (int i = 0; i < nvertex; ++i){
			nodes[i] = new Node(i);
			que->insert(nodes[i]);
		}
		que->decreaseKey(nodes[start], 0.0);
		Node* ptr = que->extractMin();
		while (ptr != NULL){
			Edge* e = graph.getEdge(ptr->id);
			while (e != NULL){
			if ((nodes[e->end]->value) > (ptr->value + e->weight)){
					nodes[e->end]->way = ptr;
					que->decreaseKey(nodes[e->end], ptr->value + e->weight); 
				}
			e = e->next;
			}
			ptr = que->extractMin(); 
		}

		std::cout << "Algorytm Dijkstra dla wierzcholka " << start << std::endl;
		for (int i = 0; i < nvertex; i++){
			if (i == start)
				continue;
			system("pause");
			std::cout << "Droga od " << start << " do " << i << std::endl << "Koszt " << nodes[i]->value << std::endl << "Sciezka (od dolu): "<<std::endl;
			Node *tmp = nodes[i];
			while (tmp != NULL){
				Node *tmp2 = tmp->way;
				if (tmp2 != NULL){
					std::cout << "od "<< tmp2->id << " do " << tmp->id << " " << tmp->value - tmp2->value << std::endl;
					tmp = tmp2;
				}else
					break;
			}
			std::cout<<std::endl;
		}
		for (int i = 0; i < nvertex; ++i){
			delete(nodes[i]);
		}
		delete[] nodes;
		delete que;
	}
}
#endif