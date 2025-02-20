import java.util.*;

public class VotingSystem {
    private Map<String, Integer> voteMap = new HashMap<>();
    private LinkedHashMap<String, Integer> orderedVoteMap = new LinkedHashMap<>();
    private TreeMap<String, Integer> sortedVoteMap = new TreeMap<>();

    public void castVote(String candidate) {
        voteMap.put(candidate, voteMap.getOrDefault(candidate, 0) + 1);
        orderedVoteMap.put(candidate, orderedVoteMap.getOrDefault(candidate, 0) + 1);
        sortedVoteMap.put(candidate, sortedVoteMap.getOrDefault(candidate, 0) + 1);
    }

    public void displayVotesInOrder() {
        System.out.println("\nVotes in Order of Voting:");
        for (Map.Entry<String, Integer> entry : orderedVoteMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void displaySortedResults() {
        System.out.println("Sorted Voting Results:");
        for (Map.Entry<String, Integer> entry : sortedVoteMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void displayFinalResults() {
        System.out.println("Final Vote Count:");
        for (Map.Entry<String, Integer> entry : voteMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();

        votingSystem.castVote("Voter1");
        votingSystem.castVote("Voter2");
        votingSystem.castVote("Voter3");
        votingSystem.castVote("Voter4");
        votingSystem.castVote("Voter5");
        votingSystem.castVote("Voter6");

        votingSystem.displayVotesInOrder();
        votingSystem.displaySortedResults();
        votingSystem.displayFinalResults();
    }
}
