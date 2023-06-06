#ifndef GRAPH_H
#define GRAPH_H
#include <ostream>
#include "edge.h"

class Graph{
private:
	Edge **edges;
	int nedge, nvertex;
public:
	Graph(int vertex) : nedge(0), nvertex(vertex), edges(new Edge*[vertex]){
		for (int i = 0; i < nvertex; ++i)
			edges[i] = NULL;
	}
	void addDirectedEdge(int start, int end, double weight);
	void addUnDirectedEdge(int start, int end, double weight);
	const int getNedge() const;
	const int getNvertex() const;
	Edge* getEdge(int start);
	friend std::ostream& operator<< (std::ostream&, Graph const&);
	~Graph();
};

const int Graph::getNedge() const{
	return nedge;
}

const int Graph::getNvertex() const{
	return nvertex;
}

void Graph::addDirectedEdge(int start, int end, double weight){
	Edge * temp = new Edge(start, end, weight);
	temp->next = edges[start];
	edges[start] = temp;
	++nedge;
}

void Graph::addUnDirectedEdge(int start, int end, double weight){
	addDirectedEdge(start, end, weight);
	addDirectedEdge(end, start, weight);
}
Edge* Graph::getEdge(int start){
	return edges[start];
}
std::ostream& operator<< (std::ostream& out, Graph const& graph){
	double suma = 0;
	for (int i = 0; i<graph.getNvertex(); i++){
		Edge* e = graph.edges[i];
		while (e != NULL){
			out << e->start << " " << e->end << " " << e->weight<< std::endl;
			suma += e->weight;
			e = e->next;
		}
	}
	out << "Suma wag krawedzi: " << suma << std::endl;
	return out;
}

Graph::~Graph(){
	for (int i = 0; i<nvertex; i++){
		Edge* e = edges[i];
		while (e != NULL){
			Edge *temp = e; 
			e = e->next;
			delete temp;
		}
	}
	delete[] edges;
}

#endif