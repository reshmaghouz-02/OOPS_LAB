AI BASED CODE PLAGIARISM DETECTOR
Program:
import java.util.*;

class CodeSubmission {
    private String studentName;
    private String sourceCode;

    public CodeSubmission(String studentName, String sourceCode) {
        this.studentName = studentName;
        this.sourceCode = sourceCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}

class CodePreprocessor {
    public List<String> processCode(String code) {
        // Remove single-line comments
        code = code.replaceAll("//.*", "");

        // Convert to lowercase
        code = code.toLowerCase();

        // Replace special characters with spaces
        code = code.replaceAll("[^a-z0-9_]+", " ");

        // Split code into tokens
        String[] tokens = code.trim().split("\s+");

        return new ArrayList<>(Arrays.asList(tokens));
    }
}

class SimilarityEngine {
    private CodePreprocessor preprocessor;

    public SimilarityEngine() {
        preprocessor = new CodePreprocessor();
    }

    public double calculateSimilarity(CodeSubmission code1,
                                      CodeSubmission code2) {
        List<String> tokens1 =
            preprocessor.processCode(code1.getSourceCode());

        List<String> tokens2 =
            preprocessor.processCode(code2.getSourceCode());

        Set<String> set1 = new HashSet<>(tokens1);
        Set<String> set2 = new HashSet<>(tokens2);

        Set<String> commonTokens = new HashSet<>(set1);
        commonTokens.retainAll(set2);

        Set<String> allTokens = new HashSet<>(set1);
        allTokens.addAll(set2);

        if (allTokens.isEmpty()) {
            return 0.0;
        }

        return ((double) commonTokens.size()
                / allTokens.size()) * 100;
    }
}

class PlagiarismReport {
    public void generateReport(CodeSubmission code1,
                               CodeSubmission code2,
                               double similarity) {
        System.out.println("\n======================================");
        System.out.println(" AI BASED CODE PLAGIARISM DETECTOR");
        System.out.println("======================================");
        System.out.println("Student 1 : "
                + code1.getStudentName());
        System.out.println("Student 2 : "
                + code2.getStudentName());
        System.out.printf("Similarity : %.2f%%\n",
                similarity);

        if (similarity >= 80) {
            System.out.println("Status : HIGH PLAGIARISM");
        }
        else if (similarity >= 50) {
            System.out.println("Status : MODERATE PLAGIARISM");
        }
        else {
            System.out.println("Status : LOW PLAGIARISM");
        }

        System.out.println("======================================");
    }
}

class PlagiarismDetector {
    private SimilarityEngine engine;
    private PlagiarismReport report;

    public PlagiarismDetector() {
        engine = new SimilarityEngine();
        report = new PlagiarismReport();
    }

    public void detect(CodeSubmission code1,
                       CodeSubmission code2) {
        double similarity =
            engine.calculateSimilarity(code1, code2);
        report.generateReport(code1, code2, similarity);
    }
}

public class Main {
    public static void main(String[] args) {
        String code1 =
            "int sum = 0;" +
            " for(int i = 0; i < 10; i++)" +
            " sum = sum + i;";

        String code2 =
            "int total = 0;" +
            " for(int i = 0; i < 10; i++)" +
            " total = total + i;";

        CodeSubmission student1 =
            new CodeSubmission(
                "Student A",
                code1);

        CodeSubmission student2 =
            new CodeSubmission(
                "Student B",
                code2);

        PlagiarismDetector detector =
            new PlagiarismDetector();

        detector.detect(student1, student2);
    }
}
Output:
======================================
AI BASED CODE PLAGIARISM DETECTOR
======================================
Student 1 : Student A
Student 2 : Student B
Similarity : 66.67%
Status : MODERATE PLAGIARISM
======================================
