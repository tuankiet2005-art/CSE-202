import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;

public class EIMINDISTA {

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        int n = rd.nextInt();
        int m = rd.nextInt();

        Vertex[] graph = new Vertex[n];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            long w = rd.nextLong();
            graph[u].adjVertex.add(new Edge(graph[v], w));
            graph[v].adjVertex.add(new Edge(graph[u], w));     
        }

        dijkstra(graph[0], graph);

        for (int i = 1; i < graph.length; i++) {
            if(graph[i].dis == Long.MAX_VALUE) {
                sb.append(-1).append(" ");
            } else sb.append(graph[i].dis).append(" ");
        }

        System.out.println(sb);

    }

    static void dijkstra(Vertex source, Vertex[] adj) {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>((v1,v2) -> Long.compare(v1.dis, v2.dis));

        source.dis = 0;
        priorityQueue.add(source);

        while(!priorityQueue.isEmpty()) {
            Vertex u = priorityQueue.poll();
            Vertex originVertex = adj[u.id];
            for (Edge edge : originVertex.adjVertex) {
                Vertex v = edge.endVertex;
                long alt = edge.weight + u.dis;
                if(alt < v.dis) {
                    v.dis = alt;
                    Vertex clone = new Vertex(v.id);
                    clone.dis = v.dis;

                    priorityQueue.add(clone);
                }
            }
        }
    }

    static class Vertex {
        int id;
        List<Edge> adjVertex = new ArrayList<>();
        boolean visited;
        long dis = Long.MAX_VALUE;

        public Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {
        long weight;
        Vertex endVertex;

        public Edge(Vertex endVertex, long weight) {
            this.endVertex = endVertex;
            this.weight = weight;
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