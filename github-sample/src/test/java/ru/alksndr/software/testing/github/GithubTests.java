package ru.alksndr.software.testing.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("78eac0a9980649bec6f347e3aa7ed62392ecd945");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("flntyyy", "software_testing")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
