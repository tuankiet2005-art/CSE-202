package week5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class WTRABS {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);

        int n = rd.nextInt();

        double[] k = new double[n];

        for (int i = 0; i < n; i++) {
            k[i] = rd.nextDouble();
        }

        Vertex[] graph = new Vertex[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new Vertex(i);
            graph[i].amountWater = k[i];
        }

        for (int i = 0; i < n - 1; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[v].addAdj(graph[u]);
        }
        dfs(graph[0]);
        for (Vertex vertex : graph) {
            Collections.sort(vertex.adjVertexs, ((s1,s2) -> Integer.compare(s1.id, s2.id)));
            if(vertex.amountWater != 0) {
                sb.append(vertex.id).append(" ").append(vertex.amountWater).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v){
        v.visited = true;

        double temp = 0;
        if(v.adjVertexs.size() > 0) {
            temp = v.amountWater / v.adjVertexs.size();
            v.amountWater = 0;
        } else return;

        for (Vertex adjVertex : v.adjVertexs) {
            if (!adjVertex.visited) {
                adjVertex.amountWater += temp;
                dfs(adjVertex);          
            }
        }
    }

    static class Vertex {
        int id;
        boolean visited;
        List<Vertex> adjVertexs = new ArrayList<>();
        double amountWater = 0;

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
