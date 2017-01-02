package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by juan on 15/09/16.
 * Katana Java SDK
 */
public class Transaction {

    @JsonProperty("c")
    private List<ServiceTransaction> commit;

    @JsonProperty("r")
    private List<ServiceTransaction> rollback;

    @JsonProperty("C")
    private List<ServiceTransaction> complete;

    public Transaction() {
    }

    public List<ServiceTransaction> getCommit() {
        return commit;
    }

    public void setCommit(List<ServiceTransaction> commit) {
        this.commit = commit;
    }

    public List<ServiceTransaction> getRollback() {
        return rollback;
    }

    public void setRollback(List<ServiceTransaction> rollback) {
        this.rollback = rollback;
    }

    public List<ServiceTransaction> getComplete() {
        return complete;
    }

    public void setComplete(List<ServiceTransaction> complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) o;

        if (getCommit() != null ? !getCommit().equals(that.getCommit()) : that.getCommit() != null) {
            return false;
        }
        if (getRollback() != null ? !getRollback().equals(that.getRollback()) : that.getRollback() != null) {
            return false;
        }
        return getComplete() != null ? getComplete().equals(that.getComplete()) : that.getComplete() == null;

    }

    @Override
    public int hashCode() {
        int result = getCommit() != null ? getCommit().hashCode() : 0;
        result = 31 * result + (getRollback() != null ? getRollback().hashCode() : 0);
        result = 31 * result + (getComplete() != null ? getComplete().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "commit=" + commit +
                ", rollback=" + rollback +
                ", complete=" + complete +
                '}';
    }

    public Transaction(Transaction other) {
        this.commit = other.commit;
        this.rollback = other.rollback;
        this.complete = other.complete;
    }
}
