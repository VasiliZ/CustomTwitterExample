package com.github.vasiliz.VasiliZTwitterApp.hashtags;

public class HashtagInteractorImpl implements HashtagInteracror {
    private HashtagRepository mHashtagRepository;

    public HashtagInteractorImpl(HashtagRepository pHashtagRepository) {
        mHashtagRepository = pHashtagRepository;
    }

    @Override
    public void execute() {
        mHashtagRepository.getHashtags();
    }
}
