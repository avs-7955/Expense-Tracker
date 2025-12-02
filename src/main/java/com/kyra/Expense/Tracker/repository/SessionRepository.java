package com.kyra.Expense.Tracker.repository;

import com.kyra.Expense.Tracker.db.Session;
import com.kyra.Expense.Tracker.db.User;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM session " +
            "WHERE id = ( " +
            "    SELECT id " +
            "    FROM session " +
            "    WHERE user_id =:userId " +
            "    ORDER BY updated_at ASC " +
            "    LIMIT 1 " +
            ") ", nativeQuery = true)
    void deleteLeastRecentlyUsedSession(@NonNull Long userId);
}

