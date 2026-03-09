package week6;

import java.io.*;
import java.util.*;

class EILOCAL {
    static InputReader sc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sc = new InputReader(System.in);
        int n = sc.nextInt();
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodeList.add(new Node(i));
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int distance = sc.nextInt();

            nodeList.get(u).addNeighbor(nodeList.get(v));
            nodeList.get(v).addNeighbor(nodeList.get(u));

            nodeList.get(u).distanceMap.put(v, distance);
            nodeList.get(v).distanceMap.put(u, distance);
        }
        Node start = nodeList.get(0);
        for (Node w : nodeList) {
            if (w.id == 0) {
                start = w;
                break;
            }
        }
        findSolution(start);
        int max = -1;
        int id = 0;
        for (Node node : nodeList) {
            if (max < node.distance) {
                max = node.distance;
                id = node.id;
            }
        }
        for (Node i : nodeList) {
            i.distance = 0;
            i.isVisited = false;
        }
        findSolution1(nodeList.get(id));
        int max1 = -1;
        int id2 = -1;
        Node end = null;
        for (Node node : nodeList) {
            if (max1 < node.distance) {
                max1 = node.distance;
                end = node;
                id2 = node.id;
            }
        }
        if (id2 < id)
            id = id2;
        if (end != null) {
            System.out.println(id + " " + end.distance);
        }
    }

    public static void findSolution(Node start) {
        start.isVisited = true;
        for (Node child : start.nodeList) {
            if (!child.isVisited) {
                child.distance = start.distanceMap.get(child.id);
                if (child.distance <= start.distance + child.distance) {
                    child.distance += start.distance;
                }
                findSolution(child);
            }
        }
    }

    public static void findSolution1(Node start) {
        start.isVisited = true;
        for (Node child : start.nodeList) {
            if (!child.isVisited) {
                child.distance = start.distanceMap.get(child.id);
                if (child.distance <= start.distance + child.distance) {
                    child.distance += start.distance;
                }
                findSolution(child);
            }
        }
    }

    static class Node {
        int id;
        boolean isVisited;
        int distance;
        List<Node> nodeList;

        HashMap<Integer, Integer> distanceMap;

        public Node(int id) {
            this.id = id;
            this.distance = 0;
            this.nodeList = new ArrayList<>();
            this.distanceMap = new HashMap<>();
            this.isVisited = false;
        }

        public void addNeighbor(Node node) {
            this.nodeList.add(node);
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

}




