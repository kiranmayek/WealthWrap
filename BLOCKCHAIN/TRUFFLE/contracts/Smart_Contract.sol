// SPDX-License-Identifier: MIT

pragma solidity ^0.8.19;

import "https://github.com/OpenZeppelin/openzeppelin-solidity/contracts/access/Ownable.sol";

contract WealthWrap is Ownable {
    struct User {
        uint256 id;
        string name;
        string[] learningModules;
        uint256[] completedModules;
        uint256 cgpa;
        uint256 totalPoints;
        uint256 rank;
    }

    User[] public users;
    uint256 public nextUserId;

    struct Module {
        uint256 id;
        string name;
        string description;
        string content;
        string[] caseStudies;
        string[] learningPaths;
        uint256[] prerequisites;
    }

    Module[] public modules;
    uint256 public nextModuleId;

    struct CaseStudy {
        uint256 id;
        string title;
        string description;
        string solution;
    }

    CaseStudy[] public caseStudies;
    uint256 public nextCaseStudyId;

    struct LearningPath {
        uint256 id;
        string name;
        Module[] modules;
    }

    LearningPath[] public learningPaths;
    uint256 public nextLearningPathId;

    struct PointsReward {
        uint256 moduleCompletion;
        uint256 caseStudyCompletion;
        uint256 leaderboardPosition;
    }

    PointsReward public pointsReward;

    event UserCreated(uint256 userId);
    event ModuleAdded(uint256 moduleId);
    event CaseStudyAdded(uint256 caseStudyId);
    event LearningPathAdded(uint256 learningPathId);

    constructor() {
        addModule(
            "Introduction to Financial Literacy",
            "An overview of financial literacy and its importance.",
            "Welcome to the world of financial literacy! This module provides an introduction to financial literacy and its significance in everyday life.",
            [],
            [],
            []
        );

        addCaseStudy(
            "Saving for Retirement",
            "A case study on the importance of saving for retirement.",
            "John and Jane, a couple in their 30s, realized the importance of saving for retirement early. They started investing a portion of their income in a diversified portfolio and were able to retire comfortably in their 60s.",
            "John and Jane, a couple in their 30s, realized the importance of saving for retirement early. They started investing a portion of their income in a diversified portfolio and were able to retire comfortably in their 60s."
        );

        addLearningPath(
            "Personal Finance Basics",
            new Module[](
                modules[0]
            )
        );
    }

    function addUser(string memory _name) public onlyOwner {
        User memory newUser = User(nextUserId, _name, new string[](0), new uint256[](0), 0, 0, 0);
        users.push(newUser);
        nextUserId++;
        emit UserCreated(nextUserId - 1);
    }

    function getUserById(uint256 _id) public view returns (User memory) {
        require(_id < users.length, "User ID does not exist");
        return users[_id];
    }

    function addModule(
        string memory _name,
        string memory _description,
        string memory _content,
        string[] memory _caseStudies,
        string[] memory _learningPaths,
        uint256[] memory _prerequisites
    ) public onlyOwner {
        Module memory newModule = Module(nextModuleId, _name, _description, _content, _caseStudies, _learningPaths, _prerequisites);
        modules.push(newModule);
        nextModuleId++;
        emit ModuleAdded(nextModuleId - 1);
    }

    function getModuleById(uint256 _id) public view returns (Module memory) {
        require(_id < modules.length, "Module ID does not exist");
        return modules[_id];
    }

    function addCaseStudy(
        string memory _title,
        string memory _description,
        string memory _solution,
        string memory _content
    ) public onlyOwner {
        CaseStudy memory newCaseStudy = CaseStudy(nextCaseStudyId, _title, _description, _solution);
        caseStudies.push(newCaseStudy);
        nextCaseStudyId++;
        emit CaseStudyAdded(nextCaseStudyId - 1);
    }

    function getCaseStudyById(uint256 _id) public view returns (CaseStudy memory) {
        require(_id < caseStudies.length, "Case Study ID does not exist");
        return caseStudies[_id];
    }

    function addLearningPath(
        string memory _name,
        Module[] memory _modules
    ) public onlyOwner {
        LearningPath memory newLearningPath = LearningPath(nextLearningPathId, _name, _modules);
        learningPaths.push(newLearningPath);
        nextLearningPathId++;
        emit LearningPathAdded(nextLearningPathId - 1);
    }

    function getLearningPathById(uint256 _id) public view returns (LearningPath memory) {
        require(_id < learningPaths.length, "Learning Path ID does not exist");
        return learningPaths[_id];
    }

    function assignModuleToUser(uint256 _userId, uint256 _moduleId) public onlyOwner {
        User storage user = users[_userId];
        Module storage module = modules[_moduleId];

        user.learningModules.push(module.name);
    }

    function markModuleCompleted(uint256 _userId, uint256 _moduleId) public onlyOwner {
        User storage user = users[_userId];
        Module storage module = modules[_moduleId];

        uint256 index = findModuleIndex(user.learningModules, module.name);
        require(index != uint256(-1), "Module not assigned to the user");

        user.completedModules.push(_moduleId);
        user.cgpa += calculateCgpa(module.id);
    }

    function markCaseStudyCompleted(uint256 _userId, uint256 _caseStudyId) public onlyOwner {
        User storage user = users[_userId];

        CaseStudy storage caseStudy = caseStudies[_caseStudyId];

        uint256 points = calculatePoints(caseStudy.id);
        user.totalPoints += points;
    }

    function calculateCgpa(uint256 _moduleId) internal view returns (uint256) {
        Module storage module = modules[_moduleId];

        uint256 completedModulesCount = uint256(module.completedModules.length);
        uint256 totalPoints = 0;

        for (uint256 i = 0; i < completedModulesCount; i++) {
            totalPoints += modules[module.completedModules[i]].points;
        }

        return totalPoints / completedModulesCount;
    }

    function calculatePoints(uint256 _caseStudyId) internal view returns (uint256) {
        CaseStudy storage caseStudy = caseStudies[_caseStudyId];

        uint256 points = 0;

        if (caseStudy.solution == "Correct Solution") {
            points = 10;
        } else {
            points = 5;
        }

        return points;
    }

    function findModuleIndex(string[] memory _moduleNames, string memory _moduleName) internal view returns (uint256) {
        for (uint256 i = 0; i < _moduleNames.length; i++) {
            if (keccak256(abi.encodePacked(_moduleNames[i])) == keccak256(abi.encodePacked(_moduleName))) {
                return i;
            }
        }

        return uint256(-1);
    }
}