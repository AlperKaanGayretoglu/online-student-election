package com.alpergayretoglu.online_student_election.service;

import com.alpergayretoglu.online_student_election.constants.ApplicationMessages;
import com.alpergayretoglu.online_student_election.exception.EntityNotFoundException;
import com.alpergayretoglu.online_student_election.model.entity.EdevletUser;
import com.alpergayretoglu.online_student_election.model.entity.Election;
import com.alpergayretoglu.online_student_election.model.entity.User;
import com.alpergayretoglu.online_student_election.model.enums.UserRole;
import com.alpergayretoglu.online_student_election.repository.ElectionRepository;
import com.alpergayretoglu.online_student_election.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EdevletUserService edevletUserService;
    private final ElectionService electionService;
    private final ElectionRepository electionRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return getUserWithException(id);
    }

    public void deleteUser(String userId) {
        User user = getUserWithException(userId);
        userRepository.delete(user);
    }

    public String applyForCandidacy(String userId) {
        User user = getUserWithException(userId);

        Election election = electionService.getAllElectionsForCurrentTerm().stream()
                .filter(elect -> elect.getDepartment().equals(user.getDepartment()))
                .findFirst().orElse(null);

        if (election == null) {
            throw new RuntimeException(ApplicationMessages.CANDIDATE_APPLICATION_SUBMIT_FAIL_NO_ELECTION);
        }

        if (user.getRole() == UserRole.CANDIDATE) {
            throw new RuntimeException(ApplicationMessages.CANDIDATE_APPLICATION_SUBMIT_FAIL_ALREADY_CANDIDATE);
        }

        EdevletUser edevletUser = edevletUserService.getEdevletUserByTcNo(user.getTcNo());

        if (!edevletUser.isEligible()) {
            return ApplicationMessages.CANDIDATE_APPLICATION_SUBMIT_SUCCESS;
        }

        user.setRole(UserRole.CANDIDATE);
        userRepository.save(user);

        election.addCandidate(user);
        electionRepository.save(election);
        return ApplicationMessages.CANDIDATE_APPLICATION_SUBMIT_SUCCESS;
    }

    private User getUserWithException(String id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
    }

    public String getApplicationStatus(String userId) {
        User user = getUserWithException(userId);
        return user.getRole() == UserRole.CANDIDATE ?
                ApplicationMessages.CANDIDATE_APPLICATION_STATUS_ACCEPTED :
                ApplicationMessages.CANDIDATE_APPLICATION_STATUS_REJECTED;
    }

    public String withdrawFromCandidacy(String userId) {
        User user = getUserWithException(userId);

        if (user.getRole() != UserRole.CANDIDATE) {
            throw new EntityNotFoundException(ApplicationMessages.CANDIDATE_WITHDRAW_FAIL_NOT_CANDIDATE);
        }

        Election election = electionService.getAllElectionsForCurrentTerm().stream()
                .filter(elect -> elect.getCandidates().stream().anyMatch(cand -> cand.getId().equals(user.getId())))
                .findFirst().orElse(null);

        if (election == null) {
            throw new EntityNotFoundException("User is a candidate but no election found for him/her.");
        }

        if (election.getStartDate().isBefore(LocalDateTime.now())) {
            throw new EntityNotFoundException(ApplicationMessages.CANDIDATE_WITHDRAW_FAIL_ELECTION_STARTED);
        }

        user.setRole(UserRole.VOTER);
        userRepository.save(user);

        election.removeCandidate(user);
        electionRepository.save(election);

        return ApplicationMessages.CANDIDATE_WITHDRAW_SUCCESS;
    }
}