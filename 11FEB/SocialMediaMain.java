import java.util.ArrayList;
import java.util.List;

class User {
    private int userId;
    private String name;
    private int age;
    private List<Integer> friendIds;
    private User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Integer> getFriendIds() {
        return friendIds;
    }

    public void addFriend(int friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    public void removeFriend(int friendId) {
        friendIds.remove((Integer) friendId);
    }

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Age: " + age + ", Friends: " + friendIds;
    }
}

class SocialMediaConnections {
    private User head;

    public SocialMediaConnections() {
        this.head = null;
    }

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newUser);
        }
    }

    public User searchUserById(int userId) {
        User current = head;
        while (current != null) {
            if (current.getUserId() == userId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public User searchUserByName(String name) {
        User current = head;
        while (current != null) {
            if (current.getName().equalsIgnoreCase(name)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = searchUserById(userId1);
        User user2 = searchUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.addFriend(userId2);
            user2.addFriend(userId1);
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = searchUserById(userId1);
        User user2 = searchUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.removeFriend(userId2);
            user2.removeFriend(userId1);
        }
    }

    public List<Integer> findMutualFriends(int userId1, int userId2) {
        User user1 = searchUserById(userId1);
        User user2 = searchUserById(userId2);
        List<Integer> mutualFriends = new ArrayList<>();
        if (user1 != null && user2 != null) {
            for (int friendId1 : user1.getFriendIds()) {
                if (user2.getFriendIds().contains(friendId1)) {
                    mutualFriends.add(friendId1);
                }
            }
        }
        return mutualFriends;
    }

    public void displayAllFriends(int userId) {
        User user = searchUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.getName() + ": " + user.getFriendIds());
        }
    }

    public int countFriends(int userId) {
        User user = searchUserById(userId);
        return (user != null) ? user.getFriendIds().size() : 0;
    }

    public void displayAllUsers() {
        User current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
}

public class SocialMediaMain {
    public static void main(String[] args) {
        SocialMediaConnections smc = new SocialMediaConnections();
        smc.addUser(1, "Alice", 25);
        smc.addUser(2, "Bob", 30);
        smc.addUser(3, "Charlie", 20);

        smc.addFriendConnection(1, 2);
        smc.addFriendConnection(1, 3);

        System.out.println("Displaying all users:");
        smc.displayAllUsers();
        System.out.println();
        
        System.out.println("Displaying all friends of Alice:");
        smc.displayAllFriends(1);
        System.out.println();
        
        System.out.println("Finding mutual friends between Alice and Bob:");
        List<Integer> mutualFriends = smc.findMutualFriends(1, 2);
        System.out.println("Mutual Friends: " + mutualFriends);
        System.out.println();
        
        System.out.println("Removing friend connection between Alice and Bob:");
        smc.removeFriendConnection(1, 2);
        smc.displayAllFriends(1);
        System.out.println();
        
        System.out.println("Counting friends of Alice:");
        int friendCount = smc.countFriends(1);
        System.out.println("Number of friends: " + friendCount);
    }
}