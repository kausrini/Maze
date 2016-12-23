package maze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Maze {

	public static class Vertex {
		int x, y;

		Vertex() {
			this.x = -1;
			this.y = -1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object v) {
			if (this.x == ((Vertex) v).x && this.y == ((Vertex) v).y)
				return true;
			else
				return false;
		}

	}

	public static class Node {

		char value;
		Vertex v;
		int distance;
		Vertex parent;

		Node() {
			this.v = new Vertex();
			this.parent = new Vertex();
			this.value = '#';
			this.distance = -2;
		}

	}

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int m, n;

		String[] input = new String[5000];

		m = 0;
		n = 0;

		while (in.hasNext()) {

			input[m++] = in.nextLine();

		}

		n = input[0].length();

		in.close();

		for (String s : args) {
			if (Integer.parseInt(s) == 1) { // Task 1
				if (isLegal(input, m, n))
					System.out.println("YES");
				else
					System.out.println("NO");
			}

			if (Integer.parseInt(s) == 2) { // Task 2
				if (hasSolution(input, m, n))
					System.out.println("YES");
				else
					System.out.println("NO");

			}

			if (Integer.parseInt(s) == 3) { // Task 3
				giveSolution(input, m, n);
			}

			if (Integer.parseInt(s) == 4) { // Task 4
				giveTeleSolution(input, m, n);
			}

		}

	}

	public static boolean isLegal(String[] input, int m, int n) {

		int sCount = 0, dCount = 0;

		if (m <= 0 || n <= 0)
			return false;

		for (int i = 0; i < m && sCount < 2 && dCount < 2; ++i) {

			if (input[i].length() != n)
				return false;

			if (input[i].contains("S"))
				sCount++;

			if (input[i].contains("D"))
				dCount++;

			for (int j = 0; j < n; ++j) {
				if (input[i].charAt(j) != 'S' && input[i].charAt(j) != 'D' && input[i].charAt(j) != '#'
						&& input[i].charAt(j) != '.') {

					return false;

				}
			}
		}

		if (sCount != 1 || dCount != 1) // S and D must be present only once.
			return false;

		return true;

	}

	public static boolean hasSolution(String[] input, int m, int n) {

		// The following algorithm is an implementation of Breadth First
		// Algorithm

		Node node;
		boolean flag = true;

		List<Node> maze = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();

		// Need to find Source and set distance as 0
		// populating the maze
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				node = new Node();
				node.value = input[i].charAt(j);
				node.v.x = i;
				node.v.y = j;
				if (node.value == 'S') {
					node.distance = 0;
					q.add(node); // Source added to the queue
				} else if (node.value == '#') {
					node.distance = -1;
				}
				maze.add(node);
			}
		}

		int index = -1;
		Node tempNode = null;

		// Traverse the maze and form the link from Source to Destination

		while (!q.isEmpty() && flag) {
			Node current = q.remove();
			for (int i = current.v.x - 1; i <= current.v.x + 1 && flag; ++i) {

				if (i < 0 || i >= m)
					continue;

				for (int j = current.v.y - 1; j <= current.v.y + 1 && flag; j++) {

					if (j < 0 || j >= n)
						continue;

					if ((i == current.v.x - 1 && j == current.v.y - 1) || (i == current.v.x - 1 && j == current.v.y + 1)
							|| (i == current.v.x + 1 && j == current.v.y - 1)
							|| (i == current.v.x + 1 && j == current.v.y + 1))
						continue;

					index = j + i * n;

					if (maze.get(index).distance == -2) {

						tempNode = new Node();
						tempNode.value = maze.get(index).value;
						tempNode.v.x = maze.get(index).v.x;
						tempNode.v.y = maze.get(index).v.y;
						tempNode.parent.x = current.v.x;
						tempNode.parent.y = current.v.y;
						tempNode.distance = current.distance + 1;

						maze.set(index, tempNode);
						q.add(tempNode);

						if (tempNode.value == 'D')
							flag = false;
					}

				}

			}

		}
		return !flag;
	}

	public static void giveSolution(String[] input, int m, int n) {

		// The following algorithm is an implementation of Breadth First
		// Algorithm

		Node node = null;
		Boolean flag = false;

		List<Node> maze = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				node = new Node();
				node.value = input[i].charAt(j);
				node.v.x = i;
				node.v.y = j;
				if (node.value == 'S') {
					node.distance = 0;
					q.add(node); // Source added to the queue
				} else if (node.value == '#') {
					node.distance = -1;
				}
				maze.add(node);
			}
		}

		int index = -1;
		Node tempNode = null;
		Node destination = null;

		while (!q.isEmpty()) {
			Node current = q.remove();
			for (int i = current.v.x - 1; i <= current.v.x + 1; ++i) {

				if (i < 0 || i >= m)
					continue;

				for (int j = current.v.y - 1; j <= current.v.y + 1; j++) {

					if (j < 0 || j >= n)
						continue;

					if ((i == current.v.x - 1 && j == current.v.y - 1) || (i == current.v.x - 1 && j == current.v.y + 1)
							|| (i == current.v.x + 1 && j == current.v.y - 1)
							|| (i == current.v.x + 1 && j == current.v.y + 1))
						continue;

					index = j + i * n;

					if (maze.get(index).distance == -2) {

						tempNode = new Node();
						tempNode.value = maze.get(index).value;
						tempNode.v.x = maze.get(index).v.x;
						tempNode.v.y = maze.get(index).v.y;
						tempNode.parent.x = current.v.x;
						tempNode.parent.y = current.v.y;
						tempNode.distance = current.distance + 1;

						maze.set(index, tempNode);
						q.add(tempNode);

						if (tempNode.value == 'D')
							destination = tempNode;
						flag = true;
					}

				}

			}

		}

		if (flag) {
			StringBuffer revPath = new StringBuffer();

			while (destination.parent.x != -1) {
				// Mapping from destination to source, so needs strrev
				if (destination.parent.x == destination.v.x) {
					if (destination.parent.y > destination.v.y) {
						// R needs to be appended which will be flipped anyway
						revPath.append('L');
					} else
						revPath.append('R');
				} else {
					if (destination.parent.x > destination.v.x) {
						revPath.append('U');
					} else
						revPath.append('D');
				}

				index = destination.parent.y + destination.parent.x * n;
				destination = maze.get(index);
			}

			System.out.println(revPath.reverse().toString());
			return;
		}

		System.out.println("NO");
		return;
	}

	public static void giveTeleSolution(String[] input, int m, int n) {
		// The following algorithm is an implementation of Breadth First
		// Algorithm

		Node node = null;
		boolean flag = false;

		List<Node> maze = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();
		Map<Vertex, Vertex> teleporters = new HashMap<Vertex, Vertex>();
		int[] telexy = new int[20];

		for (int i = 0; i < 20; ++i) {
			telexy[i] = -1;
		}

		// In this loop adding source to q, and creating a map of teleporters
		// and creating the maze

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				node = new Node();
				node.value = input[i].charAt(j);
				node.v.x = i;
				node.v.y = j;
				if (node.value == 'S') {
					node.distance = 0;
					q.add(node); // Source added to the queue
				} else if (node.value == '#') {
					node.distance = -1;
				} else if (node.value >= '0' && node.value <= '9') {

					if (telexy[node.value - '0'] == -1) {
						telexy[node.value - '0'] = node.v.x;
						telexy[(node.value - '0') + 1] = node.v.y;
					} else {

						Vertex tempVertex = new Vertex();
						tempVertex.x = telexy[node.value - '0'];
						tempVertex.y = telexy[(node.value - '0') + 1];
						// Storing teleportation bi directional.
						teleporters.put(node.v, tempVertex);
						teleporters.put(tempVertex, node.v);
					}

				}
				maze.add(node);
			}
		}

		int index = -1;
		Node tempNode = null;
		Node destination = null;
		Boolean[] travelled = new Boolean[10];
		Arrays.fill(travelled, false);

		while (!q.isEmpty()) {

			Node current = q.remove();
			for (int i = current.v.x - 1; i <= current.v.x + 1; ++i) {

				if (i < 0 || i >= m)
					continue;

				for (int j = current.v.y - 1; j <= current.v.y + 1; j++) {

					if (j < 0 || j >= n)
						continue;

					if ((i == current.v.x - 1 && j == current.v.y - 1) || (i == current.v.x - 1 && j == current.v.y + 1)
							|| (i == current.v.x + 1 && j == current.v.y - 1)
							|| (i == current.v.x + 1 && j == current.v.y + 1))
						continue;

					index = j + i * n;

					if (maze.get(index).distance == -2) {

						tempNode = new Node();
						tempNode.value = maze.get(index).value;
						tempNode.v.x = maze.get(index).v.x;
						tempNode.v.y = maze.get(index).v.y;
						tempNode.parent.x = current.v.x;
						tempNode.parent.y = current.v.y;
						tempNode.distance = current.distance + 1;

						if (tempNode.value == 'D') {
							destination = tempNode;
						} else if (tempNode.value >= '0' && tempNode.value <= '9') {

							if (travelled[tempNode.value - '0'] == false) {

								Vertex tempVertex = new Vertex();
								tempVertex.x = tempNode.v.x;
								tempVertex.y = tempNode.v.y;

								tempNode.v.x = teleporters.get(tempVertex).x;
								tempNode.v.y = teleporters.get(tempVertex).y;
								travelled[tempNode.value - '0'] = true;
							} else {
								tempNode.parent = current.parent;
							}

						}

						maze.set(index, tempNode);
						q.add(tempNode);
						flag = true;
					}

				}

			}

		}

		if (flag) {
			StringBuffer revPath = new StringBuffer();

			while (destination.parent.x != -1) {

				Vertex tempVertex = new Vertex();
				tempVertex.x = destination.v.x;
				tempVertex.y = destination.v.y;
				if (teleporters.containsKey(tempVertex)) {
					destination.v.x = teleporters.get(tempVertex).x;
					destination.v.y = teleporters.get(tempVertex).y;
				}

				// Mapping from destination to source, so needs strrev
				if (destination.parent.x == destination.v.x) {
					if (destination.parent.y > destination.v.y) {
						// R needs to be appended which will be flipped anyway
						revPath.append('L');
					} else
						revPath.append('R');
				} else {
					if (destination.parent.x > destination.v.x) {
						revPath.append('U');
					} else
						revPath.append('D');
				}

				index = destination.parent.y + destination.parent.x * n;
				destination = maze.get(index);
			}

			System.out.println(revPath.reverse().toString());
			return;
		}

		System.out.println("NO");
		return;

	}

}
