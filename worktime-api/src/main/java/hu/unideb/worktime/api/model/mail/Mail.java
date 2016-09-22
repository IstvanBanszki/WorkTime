package hu.unideb.worktime.api.model.mail;

import java.util.List;

public class Mail {
    
    private final String fromAddress;
    private final List<String> toAddress;
    private final List<String> ccAddress;
    private final String subject;
    private final String templateBody;

    public Mail(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, String templateBody) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.ccAddress = ccAddress;
        this.subject = subject;
        this.templateBody = templateBody;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public List<String> getToAddress() {
        return toAddress;
    }

    public List<String> getCcAddress() {
        return ccAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateBody() {
        return templateBody;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.fromAddress != null ? this.fromAddress.hashCode() : 0);
        hash = 89 * hash + (this.toAddress != null ? this.toAddress.hashCode() : 0);
        hash = 89 * hash + (this.ccAddress != null ? this.ccAddress.hashCode() : 0);
        hash = 89 * hash + (this.subject != null ? this.subject.hashCode() : 0);
        hash = 89 * hash + (this.templateBody != null ? this.templateBody.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mail other = (Mail) obj;
        return (this.toAddress != null ? this.toAddress.equals(other.toAddress) : other.toAddress == null) &&
               (this.ccAddress != null ? this.ccAddress.equals(other.ccAddress) : other.ccAddress == null) &&
               (this.fromAddress != null ? this.fromAddress.equals(other.fromAddress) : other.fromAddress == null) &&
               (this.subject != null ? this.subject.equals(other.subject) : other.subject == null) &&
               (this.templateBody != null ? this.templateBody.equals(other.templateBody) : other.templateBody == null);
    }

    @Override
    public String toString() {
        return "Mail{toAddress=" + this.toAddress + ", fromAddress=" + this.fromAddress + 
               ", subject=" + this.subject + ", body=" + this.templateBody + '}';
    }
    
}
