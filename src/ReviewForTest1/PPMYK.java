package ReviewForTest1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class PPMYK {

    static Map<Integer, TreeSet<Vertex>> distanceMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);
        StringBuffer sb = new StringBuffer();

        int n = rd.nextInt();
        int m = rd.nextInt();

        Vertex[] graph = new Vertex[n];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[u].addAdj(graph[v]);
            graph[v].addAdj(graph[u]);
        }

        int u = rd.nextInt();
        int q = rd.nextInt();

        bfs(graph[u]);

        for (int i = 0; i < q; i++) {
            int level = rd.nextInt();

            

            if(distanceMap.get(level) == null) 
                sb.append(-1);
            else {
                for (Vertex vertex : distanceMap.get(level)) {
                    sb.append(vertex.id).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        Queue<Vertex> queue = new ArrayDeque<>();
        v.visited = true;
        v.level = 0;
        queue.add(v);

        TreeSet<Vertex> distanceHead = distanceMap.get(0);
        if (distanceHead == null) {
            distanceHead = new TreeSet<>((a, b) -> Integer.compare(a.id, b.id));
            distanceMap.put(0, distanceHead);
        }
        distanceHead.add(v);

        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();
            for (Vertex adjVertex : curr.adjVertexs) {
                if (!adjVertex.visited) {
                    adjVertex.visited = true;
                    adjVertex.level = curr.level + 1;

                    TreeSet<Vertex> distanceBody = distanceMap.get(adjVertex.level);
                    if (distanceBody == null) {
                        distanceBody = new TreeSet<>((a, b) -> Integer.compare(a.id, b.id));
                        distanceMap.put(adjVertex.level, distanceBody);
                    }
                    distanceBody.add(adjVertex);

                    queue.add(adjVertex);
                }
            }
        }

    }

    static class Vertex {
        int id;
        List<Vertex> adjVertexs = new ArrayList<>();
        boolean visited;
        int level;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdj(Vertex v) {
            this.adjVertexs.add(v);
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
