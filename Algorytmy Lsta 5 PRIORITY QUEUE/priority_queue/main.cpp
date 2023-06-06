#include <iostream>
#include <fstream>
#include "priority_queue.h"
#include "priority_queue_2.h"
#include "graph.h"
#include "prim.h"
#include "kruskala.h"
#include "dijkstry.h"
class haha {
public:
	double value;
	int position;
	bool operator<(const haha &p) const{
		std::cout << "haha" << std::endl;
		return this->value < p.value;
	}
};


void test1();
void test2();
void test3();
void zad2();
void zad3();
void zad4();
void main(){
//	test1();
//	zad4();
	//	zad2();
	zad3();
	system("pause");
}


void zad2(){
	char *file1 = "mst_test.txt";
	std::ifstream x(file1);
	int n;
	x >> n;
	Graph *graph = new Graph(n);
	x >> n;
	for (int i = 0; i < n; ++i){
		int temp1, temp2;
		double temp3;
		x >> temp1 >> temp2 >> temp3;
		graph->addUnDirectedEdge(temp1, temp2, temp3);
	}
	std::cout << *graph << std::endl;
	Graph * tm = Algo::Prim::generate(*graph);
	std::cout << "Prim \n" << *tm << std::endl;
	delete tm;
	tm = Algo::Kruskala::generate(*graph);
	std::cout << "Kruskala \n" << *tm << std::endl;
	delete tm;
	delete graph;
}

void zad4(){
	char *file1 = "mediumEWG.txt";
	char *file2 = "wynik.txt";
	std::ifstream x(file1);
	std::ofstream y(file2);
	int n;
	x >> n;
	Graph *graph = new Graph(n);
	x >> n;
	for (int i = 0; i < n; ++i){
		int temp1, temp2;
		double temp3;
		x >> temp1 >> temp2 >> temp3;
		graph->addUnDirectedEdge(temp1, temp2, temp3);
	}
	std::cout << *graph << std::endl;
	Graph * tm = Algo::Prim::generate(*graph);
	std::cout << "Prim \n" << *tm << std::endl;
	delete tm;
	tm = Algo::Kruskala::generate(*graph);
	std::cout << "Kruskala \n" << *tm << std::endl;
	delete tm;
	delete graph;
}

void zad3(){
	char *file1 = "a.txt";
	std::ifstream x(file1);
	int n;
	x >> n;
	Graph *graph = new Graph(n);
	x >> n;
	for (int i = 0; i < n; ++i){
		int temp1, temp2;
		double temp3;
		x >> temp1 >> temp2 >> temp3;
		graph->addDirectedEdge(temp1, temp2, temp3);
	}
	std::cout << *graph << std::endl;
	Algo::Dijksty::generate(*graph, 0);
	delete graph;
}



void test1(){
	Priority_queue<haha> *a = new Priority_queue<haha>(10, 2);
	int i = 1;

	haha* v1 = new haha();
	haha* v2 = new haha();
	haha* v3 = new haha();
	haha* v4 = new haha();

	v1->value = 2;
	v2->value = 1;
	v3->value = 4;
	v4->value = 0;
	a->insert(*v1);
	a->insert(*v2);
	a->insert(*v3);
	a->insert(*v4);
	a->decreaseKey(1, *v4);
	std::cout << a->extractMin().value << std::endl;
	std::cout << a->extractMin().value << std::endl;
	std::cout << a->extractMin().value << std::endl;
	std::cout << a->extractMin().value << std::endl;
}

void test2(){
	Priority_queue<int> *a = new Priority_queue<int>(10, 2);
	if (1<2){
		int b = 1;
		a->insert(1);
	}
	std::cout << a->extractMin() << std::endl;
}

void test3(){
	Priority_queue2<haha> *a = new Priority_queue2<haha>(10, 2);
	int i = 1;

	haha* v1 = new haha();
	haha* v2 = new haha();
	haha* v3 = new haha();
	haha* v4 = new haha();

	v1->value = 2.0;
	v2->value = 1.0;
	v3->value = 4.0;
	v4->value = 0.0;
	a->insert(v1);
	a->insert(v2);
	a->insert(v3);
	a->insert(v4);
	
	a->decreaseKey(v3, 0.3);
	std::cout << v3->position<<" "<<v3->value << std::endl;
	std::cout << a->extractMin()->value << std::endl;
	std::cout << a->extractMin()->value << std::endl;
	std::cout << a->extractMin()->value << std::endl;
	std::cout << a->extractMin()->value << std::endl;
//	std::cout << a->extractMin()->value << std::endl;
//	std::cout << a->extractMin()->value << std::endl;
//	std::cout << a->extractMin()->value << std::endl;
//	std::cout << a->extractMin()->value << std::endl;
}

