#ifndef PRIM_H
#define PRIM_H
#include "graph.h"
#include "priority_queue.h"

namespace Algo{
	class Prim{
	public:
		static Graph* generate(Graph &graph);
	};
	Graph* Prim::generate(Graph &graph){
		int nedge = graph.getNedge(), nvertex = graph.getNvertex();
		int d = nedge / nvertex;
		Priority_queue<Edge> *que = new Priority_queue<Edge>(nedge, d);
		Graph *temp = new Graph(nvertex);
		bool *visited = new bool[nedge];
		for (int i = 0; i < nedge; ++i)
			visited[i] = false;
		Edge* ptr = graph.getEdge(0);
		while (ptr != NULL){
			visited[ptr->start] = true;
			while (ptr != NULL){
				if (!visited[ptr->end])
					que->insert(*ptr);
				ptr = ptr->next;
			}
			ptr = &(que->extractMin());
			while (que->actualSize() && visited[ptr->end]){
				ptr = &que->extractMin();
			}
			if (que->actualSize() == 0 && visited[ptr->end])
				break;
			if (ptr != NULL){
				temp->addDirectedEdge(ptr->start, ptr->end, ptr->weight);
				ptr = graph.getEdge(ptr->end);
			}
		}
		delete[] visited;
		delete que;
		return temp;
	}
}
#endif